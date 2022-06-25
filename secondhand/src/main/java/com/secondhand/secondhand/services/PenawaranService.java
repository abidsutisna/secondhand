package com.secondhand.secondhand.services;

import com.secondhand.secondhand.models.entities.Penawaran;
import java.util.List;

public interface PenawaranService {
    public Penawaran addPenawaran(Penawaran penawaran);
    void updatePenawaran(Penawaran penawaran);
    void deletePenawaranById(Long id);
    Penawaran getById(Long penawaranId);
    List<Penawaran> getAllPenawaran();

    
}
