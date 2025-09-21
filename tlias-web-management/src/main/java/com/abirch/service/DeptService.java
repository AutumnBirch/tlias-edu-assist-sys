package com.abirch.service;

import com.abirch.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 将该类交给IOC容器管理
public interface DeptService {

    List<Dept> findAll();
}
