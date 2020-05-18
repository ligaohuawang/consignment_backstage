package com.qf.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.entity.Application;
import org.springframework.ui.ModelMap;

public interface IApplicationService {

    ModelMap selectPage(Page<Application> page, ModelMap map);
}
