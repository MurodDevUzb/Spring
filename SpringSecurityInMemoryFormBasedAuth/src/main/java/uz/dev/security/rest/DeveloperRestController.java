package uz.dev.security.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.dev.security.model.Developer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/developers")
public class DeveloperRestController {

    private List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L,"Mikel","Oliver"),
            new Developer(2L, "Jon", "James"),
            new Developer(3L, "Fredy", "Nikson")
    ).collect(Collectors.toList());

    @GetMapping
    @PreAuthorize("hasAnyAuthority('developer:read')")
    public List<Developer> getAll(){
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('developer:read')")
    public Developer getById(@PathVariable Long id){
        return DEVELOPERS.stream().filter(developer -> developer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('developer:write')")
    public Developer create(@RequestBody Developer developer){
        this.DEVELOPERS.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('developer:write')")
    public void delete(@PathVariable Long id){
        this.DEVELOPERS.removeIf(developer -> developer.getId().equals(id));
    }

}
