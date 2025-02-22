package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdRepository adRepository;

    @PostMapping
    public String create(@RequestBody Ad ad) {
        ad.setId(null);
        ad.lastUpdated(Date.from(Instant.now()));
        ad.setExpireTime(LocalDateTime.now());
        return adRepository.save(ad);
    }

    @GetMapping
    public List<Ad> getByPrice(@RequestParam(value = "min", defaultValue = "0") Double min, @RequestParam(value = "max", defaultValue = "10000000") Double max) {
        List<Ad> adList = adRepository.findByPrice(min, max);
        for (Ad ad:
             adList) {
            ad.setKey(null);
        }
        return adList;
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Ad ad) {
        ad.lastUpdated(Date.from(Instant.now()));
        Ad edited = adRepository.editWithKey(ad);
        if (edited != null) {
            return ResponseEntity.ok("Updated successfully!");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid secret key!!!");
    }

    @GetMapping("/{tag}")
    public List<Ad> getByTag(@PathVariable String tag) {
        return adRepository.findByTag(tag);
    }

}
