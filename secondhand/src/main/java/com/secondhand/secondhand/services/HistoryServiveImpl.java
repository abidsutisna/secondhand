package com.secondhand.secondhand.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.secondhand.secondhand.models.entities.History;
import com.secondhand.secondhand.models.repos.HistoryRepository;

@Service
public class HistoryServiveImpl implements HistoryService{
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public History addHistory(History history) {
        return this.historyRepository.save(history);
    }

    @Override
    public void updateHistory(History history) {
        this.historyRepository.findById(history.getHistoryId()).get();
        this.historyRepository.save(history);
    }

    @Override
    public void deleteHistoryById(Long id) {
        this.historyRepository.deleteById(id);
    }

    @Override
    public History getById(Long id) {
        return this.historyRepository.findById(id).get();
    }

    @Override
    public List<History> getAllHistory() {
        return this.historyRepository.findAll();
    }
    
}
