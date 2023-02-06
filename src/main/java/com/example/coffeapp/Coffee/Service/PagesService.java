package com.example.coffeapp.Coffee.Service;

import com.example.coffeapp.Coffee.Model.Pages;
import com.example.coffeapp.Coffee.Repository.PagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PagesService {

    private final PagesRepository pagesRepository;


    public Pages findById (Long id) { return pagesRepository.getReferenceById(id); }

    public Optional<Pages> getById(Long id) { return pagesRepository.findById(id);}

    public List<Pages> findAll() { return pagesRepository.findAll(); }

    public Pages save(Pages pages) { return pagesRepository.save(pages); }

    public void deleteById(Long id) { pagesRepository.deleteById(id); }

}
