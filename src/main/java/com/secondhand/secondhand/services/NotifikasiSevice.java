package com.secondhand.secondhand.services;

import com.secondhand.secondhand.models.entities.NotifikasiBid;

import java.util.List;
public interface NotifikasiSevice {
    public NotifikasiBid addNotifikasiBid(NotifikasiBid newNotifikasiBid);
    void updateNotifikasiBid(NotifikasiBid notifikasiBid);
    void deleteNotifikasiBidById(Long id);
    NotifikasiBid getById(Long bidId);
    List<NotifikasiBid> getAllNotifikasiBid();
}
