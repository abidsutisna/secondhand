package com.secondhand.secondhand.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.secondhand.secondhand.models.entities.NotifikasiBid;
import com.secondhand.secondhand.models.repos.NotifikasiBidRepository;

@Service
public class NotifikasiServiceImpl implements NotifikasiSevice{
    
    @Autowired
    private NotifikasiBidRepository notifikasiBidRepository;

    @Override
    public NotifikasiBid addNotifikasiBid(NotifikasiBid notifikasiBid) {
        return this.notifikasiBidRepository.save(notifikasiBid);
    }

    @Override
    public void updateNotifikasiBid(NotifikasiBid notifikasiBid) {
        this.notifikasiBidRepository.findById(notifikasiBid.getNotifikasiId()).get();
        this.notifikasiBidRepository.save(notifikasiBid);
    }

    @Override
    public void deleteNotifikasiBidById(Long id) {
        this.notifikasiBidRepository.deleteById(id);
    }

    @Override
    public NotifikasiBid getById(Long id) {
        return this.notifikasiBidRepository.findById(id).get();
    }

    @Override
    public List<NotifikasiBid> getAllNotifikasiBid() {
        return this.notifikasiBidRepository.findAll();
    }
}
