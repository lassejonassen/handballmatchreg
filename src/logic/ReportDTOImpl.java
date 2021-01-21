package logic;

import data.DataLayer;
import data.Match;
import data.ReportDTO;

import java.util.ArrayList;

public class ReportDTOImpl implements iReportDTO {
	DataLayer dataLayer = new DataLayer();
	
	public ArrayList<ReportDTO> read(Match match) {
		System.out.println(dataLayer.getGoalsAndSuspensions(match.getMatchID()));
	}
}
