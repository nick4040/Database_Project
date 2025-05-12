package com.example.vactionHome.repos;

import com.example.vactionHome.entity.Amenity;
import com.example.vactionHome.entity.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordsRepository extends JpaRepository<Records, Integer> {

}
