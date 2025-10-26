package com.amazonclone.test.repository;

import com.amazonclone.test.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

}
