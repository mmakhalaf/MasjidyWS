package info.masjidy.controllers;

import info.masjidy.responses.MosqueDistListResponse;
import info.masjidy.responses.MosqueListResponse;
import info.masjidy.responses.MosqueResponse;
import info.masjidy.services.MosqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/public/mosque", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MosqueGet {

    @Autowired
    private MosqueService mosqueService;

    @GetMapping(value = "/get")
    public MosqueResponse GetMosqueById(
            @RequestParam(value = "id") Long mosqueId) {
        return new MosqueResponse(mosqueService.GetMosque(mosqueId));
    }

    @GetMapping(value = "/find")
    public MosqueDistListResponse FindMosques(
            @RequestParam Double lat,
            @RequestParam Double lng,
            @RequestParam Double radius_km,
            @RequestParam(required = false) Boolean with_prayers_only) {
        if (with_prayers_only != null && with_prayers_only) {
            return new MosqueDistListResponse(mosqueService.FindMosqueWithPrayers(lat, lng, radius_km));
        } else {
            return new MosqueDistListResponse(mosqueService.FindMosques(lat, lng, radius_km));
        }
    }

    @GetMapping(value = "/all")
    public MosqueListResponse GetAllMosques(
            @RequestParam(required = false) Boolean with_prayers_only) {
        if (with_prayers_only != null && with_prayers_only) {
            return new MosqueListResponse(mosqueService.GetAllMosquesWithPrayers());
        } else {
            return new MosqueListResponse(mosqueService.GetAllMosques());
        }
    }
}
