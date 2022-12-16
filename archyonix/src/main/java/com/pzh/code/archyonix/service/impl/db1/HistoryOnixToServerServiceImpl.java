package com.pzh.code.archyonix.service.impl.db1;

import com.pzh.code.archyonix.history.HistoryOnixToServer;
import com.pzh.code.archyonix.repository.db1.HistoryOnixToServerRepository;
import com.pzh.code.archyonix.service.db1.HistoryOnixToServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HistoryOnixToServerServiceImpl implements HistoryOnixToServerService {

    private final HistoryOnixToServerRepository historyOnixToServerRepository;

    @Autowired
    public HistoryOnixToServerServiceImpl(HistoryOnixToServerRepository historyOnixToServerRepository) {
        this.historyOnixToServerRepository = historyOnixToServerRepository;
    }

    @Override
    public HistoryOnixToServer register(HistoryOnixToServer historyOnixToServer) {
        return historyOnixToServerRepository.save(historyOnixToServer);
    }
}
