package com.bingo.admin.dao;

import com.bingo.admin.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface MenuDao extends JpaRepository<Menu,Long>,JpaSpecificationExecutor<Menu> {

    @Query(value = "select m.id,m.parent_id,m.menu_name,m.perms,(case when rm.role_id is not null then true else false end) as icheck from acc_menu m \n" +
            "left join acc_role_menu rm on m.id=rm.menu_id and rm.role_id=?1",nativeQuery = true)
    List<Map<String,Object>> roleMenuTreeData(Long roleId);

    Menu getMenuById(Long id);
}
