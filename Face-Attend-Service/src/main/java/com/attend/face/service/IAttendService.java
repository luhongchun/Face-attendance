package com.attend.face.service;

import com.attend.face.common.ServerResponse;
import com.attend.face.entity.DailyAttend;
import com.attend.face.entity.DailyStudent;

public interface IAttendService {
   
    ServerResponse findAllAttend(Integer id);
    ServerResponse findAllPersion(String studentNo);
    ServerResponse create(DailyAttend dailyAttend);
}
