package com.abirch.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
public class ClazzQueryParam {
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 指定前端传过来的日期时间格式
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 指定前端传过来的日期时间格式
    private LocalDate end;
    private Integer page;
    private Integer pageSize;
}
