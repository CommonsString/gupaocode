package com.module.system.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.module.system.dto.MenuDTO;
import com.module.system.dto.mapper.MenuMapper;
import com.module.system.entity.Menu;
import com.module.system.entity.Role;
import com.module.system.repository.MenuRepository;
import com.module.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.module.system.entity.vo.*;

@Service
public class MenuServiceImpl implements MenuService {


    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;


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
            // 转换: https://blog.csdn.net/lidai352710967/article/details/81461119
            LinkedHashSet<Menu> tempList = menuRepository.findByRoles_IdOrderBySortAsc(el.getId());
            List<Menu> menuSingle = tempList.stream().collect(Collectors.toList());
            // 加入菜单
            menus.addAll(menuSingle);
        }
        // 转发成DTO
        List<MenuDTO> menuDto = menus.stream().map(menuMapper::toDto).collect(Collectors.toList());
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
            String pid = el.getPid().toString();
            if ("0".equals(pid)) {
                tree.add(el);
            }
            // 子菜单遍历
            for (MenuDTO sub : menuList) {
                if (sub.getPid() == el.getPid()) {
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
    public Object buildMenu(List<MenuDTO> menuTree) {
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
                //
                MenuMetaVo meta = new MenuMetaVo(menuVo.getName(), el.getIcon());
                menuVo.setMeta(meta);

            }
        });
        return null;
    }
}
