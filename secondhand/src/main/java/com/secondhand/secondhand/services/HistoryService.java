package com.secondhand.secondhand.services;

import com.secondhand.secondhand.models.entities.History;
import java.util.List;

public interface HistoryService {
    public History addHistory(History newHistory);
    void updateHistory(History history);
    void deleteHistoryById(Long id);
    History getById(Long historyId);
    List<History> getAllHistory();
    
}
