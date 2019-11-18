package com.zeus.service.impl;

import com.zeus.entity.Admin;
import com.zeus.dao.AdminMapper;
import com.zeus.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tumu
 * @since 2019-11-18
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
