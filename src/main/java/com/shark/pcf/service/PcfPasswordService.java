package com.shark.pcf.service;

import com.shark.pcf.entity.PcfUser;

/**
 * Created by win7 on 2014/12/27.
 */
public interface PcfPasswordService {

    void validate(PcfUser user, String password);
}
