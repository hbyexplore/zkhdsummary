package com.summary.zkhdsummary.service;

import com.summary.zkhdsummary.bean.Log;
import org.springframework.ui.Model;

public interface LogService {
    Log findLogById(int id);
}
