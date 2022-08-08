package com.basketball.playerManager;

import com.basketball.playerManager.dtos.PlayerDto;
import com.basketball.playerManager.dtos.TeamDto;
import com.basketball.playerManager.dtos.UserDto;
import com.basketball.playerManager.model.Player;
import com.basketball.playerManager.model.PlayerPositionType;
import com.basketball.playerManager.model.Team;
import com.basketball.playerManager.model.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtils {

    private static final int teamId = 10;

    private static final int userId = 5;

    public static List<Team> generateTeam() {
         return IntStream.range(0, 5).mapToObj(i ->
            new Team(i, i + " Test-Name")
        ).collect(Collectors.toList());
    }

    public static List<TeamDto> generateTeamDtoList(List<Team> teams) {
        return teams.stream().map(team -> new TeamDto(team.getName())).collect(Collectors.toList());
    }

    public static Team generateTeam(String name) {
       return new Team(teamId,
               name);
    }

    public static TeamDto generateTeamDto(String name) {
        return new TeamDto(name);
    }

    public static List<Player> generatePlayer() {
        return IntStream.range(0, 5).mapToObj(i ->
                new Player(i, "Test-Name", "Test-Surname", PlayerPositionType.PG, new Team(i, "Test-Team"))
        ).collect(Collectors.toList());
    }

    public static List<PlayerDto> generatePlayerDtoList(List<Player> playerList) {
        return playerList.stream().map(player -> new PlayerDto(player.getName(), player.getSurname(), player.getPositionType(), player.getTeam())).collect(Collectors.toList());
    }

    public static Player generatePlayer(String name, String surname, PlayerPositionType type, Team team) {
        return new Player(name,
                surname,
                type,
                team);
    }

    public static PlayerDto generatePlayerDto(String name, String surname, PlayerPositionType type, Team team) {
        return new PlayerDto(name,
                surname,
                type,
                team);
    }

    public static List<User> generateUser() {
        return IntStream.range(0, 5).mapToObj(i ->
                new User(i + "Test-UserName", "Test-Password", "Test-Mail")
        ).collect(Collectors.toList());
    }

    public static List<UserDto> generateUserDtoList(List<User> users) {
        return users.stream().map(user -> new UserDto(user.getUserName(), user.getUserName())).collect(Collectors.toList());
    }

    public static User generateUser(String userName, String password, String mail) {
        return new User(userId,
                userName,
                password,
                mail);
    }

    public static UserDto generateUserDto(String userName, String mail) {
        return new UserDto(userName,
                mail);
    }
}
