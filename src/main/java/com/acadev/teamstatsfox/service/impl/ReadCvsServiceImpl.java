package com.acadev.teamstatsfox.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.model.response.PlayerStatsFromCvs;
import com.acadev.teamstatsfox.service.ReadCvsService;
import com.acadev.teamstatsfox.utils.ConstantsUtils;

@Service
public class ReadCvsServiceImpl implements ReadCvsService {

	public ArrayList<PlayerStatsFromCvs> getStatsPlayers() {
		try {

			String pathCsv = new ClassPathResource(ConstantsUtils.PATH_RESOURCE_CVS).getFile().toPath().toString();

			ArrayList<PlayerStatsFromCvs> playerList = new ArrayList<>();
			try (Scanner scanner = new Scanner(new File(pathCsv))) {
				while (scanner.hasNextLine()) {
					// si no es titulo creo el objeto desde el siguiente row
					String newRow = scanner.nextLine();
					if (!newRow.equals(ConstantsUtils.PATH_RESOURCE_CVS_ROW_TITLE)) {
						playerList.add(getRecordFromLine(newRow));
					}
				}
			}
			return playerList;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private PlayerStatsFromCvs getRecordFromLine(String line) {
		String[] rowList = line.split(ConstantsUtils.COMMA_DELIMITER);
		PlayerStatsFromCvs player = PlayerStatsFromCvs.builder().name(rowList[0].replaceAll("\"", ""))
				.matches(Integer.valueOf(rowList[1])).goals(Integer.valueOf(rowList[2]))
				.assists(Integer.valueOf(rowList[3])).redCard(Integer.valueOf(rowList[4]))
				.yellowCard(Integer.valueOf(rowList[5])).captains(Integer.valueOf(rowList[6])).build();

		return player;
	}

}
