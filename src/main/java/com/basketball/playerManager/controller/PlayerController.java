package com.basketball.playerManager.controller;

import com.basketball.playerManager.dtos.PlayerDto;
import com.basketball.playerManager.dtos.request.CreatePlayerRequest;
import com.basketball.playerManager.dtos.request.UpdatePlayerRequest;
import com.basketball.playerManager.model.PlayerPositionType;
import com.basketball.playerManager.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerDto> save(@RequestBody @Valid CreatePlayerRequest request) {
        return new ResponseEntity<>(playerService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getAll() {
        return new ResponseEntity<>(playerService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<PlayerDto> deleteByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(playerService.deleteByName(name), HttpStatus.OK);
    }

    @GetMapping("/{position}")
    public ResponseEntity<List<PlayerDto>> getByPosition(@PathVariable("position") PlayerPositionType type) {
        return new ResponseEntity<>(playerService.getByPosition(type), HttpStatus.OK);
    }

    @PutMapping("/{name}")
    public ResponseEntity<PlayerDto> update(@PathVariable("name") String name, @RequestBody UpdatePlayerRequest request) {
        return new ResponseEntity<>(playerService.update(name, request), HttpStatus.OK);
    }
}
