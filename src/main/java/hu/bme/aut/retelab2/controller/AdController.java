package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdRepository adRepository;

    @PostMapping
    public Ad create(@RequestBody Ad ad) {
        ad.setId(null);
        ad.setCreatedAt(Date.from(Instant.now()));
        return adRepository.save(ad);
    }

    @GetMapping
    public List<Ad> getByPrice(@RequestParam Double min, @RequestParam Double max) {
        return adRepository.findByPrice(min, max);
    }
}
