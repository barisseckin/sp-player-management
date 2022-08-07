package com.basketball.playerManager.controller;

import com.basketball.playerManager.dtos.TeamDto;
import com.basketball.playerManager.dtos.request.CreateTeamRequest;
import com.basketball.playerManager.dtos.request.UpdateTeamRequest;
import com.basketball.playerManager.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamDto> save(@RequestBody @Valid CreateTeamRequest request) {
        return new ResponseEntity<>(teamService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TeamDto>> getAll() {
        return new ResponseEntity<>(teamService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<TeamDto> deleteByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(teamService.deleteByName(name), HttpStatus.OK);
    }

    @PutMapping("/{name}")
    public ResponseEntity<TeamDto> update(@PathVariable("name") String name, @RequestBody UpdateTeamRequest request) {
        return new ResponseEntity<>(teamService.update(name, request), HttpStatus.OK);
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<TeamDto> getByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(teamService.getByName(name), HttpStatus.OK);
    }
}
