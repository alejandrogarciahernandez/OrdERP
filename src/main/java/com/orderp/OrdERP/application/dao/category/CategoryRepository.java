package com.orderp.OrdERP.application.dao.category;

import com.orderp.OrdERP.application.controller.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("categoryRepository")
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
