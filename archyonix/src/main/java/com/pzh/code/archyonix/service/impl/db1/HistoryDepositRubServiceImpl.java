package com.pzh.code.archyonix.service.impl.db1;

import com.pzh.code.archyonix.history.HistoryDepositRub;
import com.pzh.code.archyonix.repository.db1.HistoryDepositRubRepository;
import com.pzh.code.archyonix.service.db1.HistoryDepositRubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HistoryDepositRubServiceImpl implements HistoryDepositRubService {
    private final HistoryDepositRubRepository historyDepositRubRepository;

    @Autowired
    public HistoryDepositRubServiceImpl(HistoryDepositRubRepository historyDepositRubRepository) {
        this.historyDepositRubRepository = historyDepositRubRepository;
    }

    @Override
    public HistoryDepositRub register(HistoryDepositRub historyDepositRub) {
        return historyDepositRubRepository.save(historyDepositRub);
    }
}
