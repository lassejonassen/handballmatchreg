package logic;

import data.Match;
import data.ReportDTO;
import java.util.ArrayList;

public interface iReportDTO {
	public ArrayList<ReportDTO> read(Match match);
}
