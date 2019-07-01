package com.lqw.service;

import com.lqw.beans.Bean;

@Bean
public class SalaryService {
    public Integer calSalary (Integer experience) {
        return experience * 1000;
    }
}
