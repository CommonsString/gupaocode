package com.module.system.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.module.system.domain.Menu;
import com.module.system.domain.Role;
import com.module.system.dto.MenuDTO;
import com.module.system.dto.translation.MenuTranslation;
import com.module.system.mapper.MenuMapper;
import com.module.system.repository.MenuRepository;
import com.module.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.module.system.entity.vo.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    // 转换器
    @Autowired
    private MenuTranslation menuTranslation;




    @Override
    public List<MenuDTO> findByRoles(Set<Role> roles) {
        Set<Menu> menus = new LinkedHashSet<>();
        for (Role role : roles) {
            List<Menu> menus1 = menuRepository.findByRoles_IdOrderBySortAsc(role.getId()).stream().collect(Collectors.toList());
            menus.addAll(menus1);
        }
        return menus.stream().map(menuTranslation::toDto).collect(Collectors.toList());
    }


    @Override
    public List<MenuVo> buildMenus(List<MenuDTO> menuDTOS) {
        List<MenuVo> list = new LinkedList<>();
        menuDTOS.forEach(menuDTO -> {
                    if (menuDTO!=null){
                        List<MenuDTO> menuDTOList = menuDTO.getChildren();
                        MenuVo menuVo = new MenuVo();
                        menuVo.setName(menuDTO.getName());
                        menuVo.setPath(menuDTO.getPath());

                        // 如果不是外链
                        if(!menuDTO.getIFrame()){
                            if(menuDTO.getPid().equals(0L)){
                                //一级目录需要加斜杠，不然访问 会跳转404页面
                                menuVo.setPath("/" + menuDTO.getPath());
                                menuVo.setComponent(StrUtil.isEmpty(menuDTO.getComponent())?"Layout":menuDTO.getComponent());
                            }else if(!StrUtil.isEmpty(menuDTO.getComponent())){
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVo(menuDTO.getName(),menuDTO.getIcon()));
                        if(menuDTOList!=null && menuDTOList.size()!=0){
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDTOList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if(menuDTO.getPid().equals(0L)){
                            MenuVo menuVo1 = new MenuVo();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
                            if(!menuDTO.getIFrame()){
                                menuVo1.setPath("index");
                                menuVo1.setName(menuVo.getName());
                                menuVo1.setComponent(menuVo.getComponent());
                            } else {
                                menuVo1.setPath(menuDTO.getPath());
                            }
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVo> list1 = new ArrayList<MenuVo>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }

    /**
     * 权限集合-->菜单
     *
     * @param roles
     * @return
     */
    @Override
    public List<MenuDTO> findByRolesForId(Set<Role> roles) {
        // 菜单集合
        Set<Menu> menus = new LinkedHashSet<Menu>();
        for (Role el : roles) {
            LinkedHashSet<Menu> tempList = menuMapper.findByRoleIdRetuenMenuList(el.getId());
            List<Menu> menuSingle = tempList.stream().collect(Collectors.toList());
            // 加入菜单
            menus.addAll(menuSingle);
        }
        // 转发成DTO
        List<MenuDTO> menuDto = menus.stream().map(menuTranslation::toDto).collect(Collectors.toList());
        return menuDto;
    }

    /**
     * @param menuList
     * @return
     */
    @Override
    public HashMap buildTree(List<MenuDTO> menuList) {
        List<MenuDTO> tree = new ArrayList<MenuDTO>();
        for (MenuDTO el : menuList) {
            // pid == 0, 顶级菜单
            if (el.getPid() == 0L) {
                tree.add(el);
            }
            // 子菜单遍历
            for (MenuDTO sub : menuList) {
//                if (sub.getId() == el.getId()) continue;
                if (sub.getPid() == el.getId()) {
                    if (el.getChildren() == null) {
                        el.setChildren(new ArrayList<MenuDTO>());
                    }
                    // 子菜单集合
                    el.getChildren().add(sub);
                }
            }
        }
        HashMap<String, Object> result = new HashMap<>();
        result.put("content", tree.size() == 0 ? menuList : tree);
        result.put("totalElements", tree != null ? tree.size() : 0);
        return result;
    }

    /**
     * @param menuTree
     * @return
     */
    @Override
    public List<MenuVo> buildMenu(List<MenuDTO> menuTree) {
        List<MenuVo> result = new LinkedList<MenuVo>();
        menuTree.forEach(el -> {
            if (el != null) {
                // 子菜单
                List<MenuDTO> childMenu = el.getChildren();
                MenuVo menuVo = new MenuVo();
                // 设置组件名称和路径
                menuVo.setName(el.getName());
                menuVo.setPath(el.getPath());
                // 非外链组件
                if (!el.getIFrame()) {
                    // 顶级目录
                    if (el.getPid() == 0L) {
                        // 头部添加‘/’, 避免404
                        menuVo.setPath("/" + el.getPath());
                        // 组件布局
                        if (StrUtil.isEmpty(el.getComponent())) {
                            // 空, 添加LayOut
                            menuVo.setComponent("Layout");
                        } else {
                            menuVo.setComponent(el.getComponent());
                        }
                    } else if (!StrUtil.isEmpty(el.getComponent())) { // 二级目录或三级...
                        menuVo.setComponent(el.getComponent());
                    }
                }
                MenuMetaVo meta = new MenuMetaVo(el.getName(), el.getIcon());
                menuVo.setMeta(meta);
                if (childMenu != null && childMenu.size() != 0) {
                    menuVo.setAlwaysShow(true);
                    menuVo.setRedirect("noredirect");
                    // 递归处理
                    menuVo.setChildren(buildMenu(childMenu));
                } else if (el.getPid() == 0L) { // 一级菜单，没有子菜单
                    MenuVo menuVo_c = new MenuVo();
                    menuVo_c.setMeta(menuVo.getMeta());
                    // 非外链
                    if (!el.getIFrame()) {
                        menuVo_c.setPath("index");
                        menuVo_c.setName(menuVo.getName());
                        menuVo_c.setComponent(menuVo.getComponent());
                    } else {
                        // 外链
                        menuVo_c.setPath(el.getPath());
                    }
                    menuVo.setName(null);
                    menuVo.setMeta(null);
                    menuVo.setComponent("Layout");
                    List<MenuVo> list = new ArrayList<MenuVo>();
                    list.add(menuVo_c);
                    // 设置子菜单
                    menuVo.setChildren(list);
                }
                result.add(menuVo);
            }
        });
        return result;
    }

    @Override
    public List<Menu> findByPid(long pid) {
        return menuMapper.findByPid(pid);
    }

    @Override
    public Object getMenuTree(List<Menu> menus) {
        List<Map<String,Object>> list = new LinkedList<>();
        menus.forEach(menu -> {
                    if (menu!=null){
                        List<Menu> menuList = menuMapper.findByPid(menu.getId());
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",menu.getId());
                        map.put("label",menu.getName());
                        if(menuList!=null && menuList.size()!=0){
                            map.put("children",getMenuTree(menuList));
                        }
                        list.add(map);
                    }
                }
        );
        return list;
    }
}
