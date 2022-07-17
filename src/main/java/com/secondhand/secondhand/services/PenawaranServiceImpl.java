package com.secondhand.secondhand.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.secondhand.secondhand.models.entities.Penawaran;
import com.secondhand.secondhand.models.repos.PenawaranRepository;

@Service
public class PenawaranServiceImpl implements PenawaranService{
    
    @Autowired
    private PenawaranRepository penawaranRepository;

    @Override
    public Penawaran addPenawaran(Penawaran penawaran) {
        return this.penawaranRepository.save(penawaran);
    }

    @Override
    public void updatePenawaran(Penawaran penawaran) {
        this.penawaranRepository.findById(penawaran.getPenawaranId()).get();
        this.penawaranRepository.save(penawaran);
    }

    @Override
    public void deletePenawaranById(Long id) {
        this.penawaranRepository.deleteById(id);
    }

    @Override
    public Penawaran getById(Long id) {
        return this.penawaranRepository.findById(id).get();
    }

    @Override
    public List<Penawaran> getAllPenawaran() {
        return this.penawaranRepository.findAll();
    }
}
