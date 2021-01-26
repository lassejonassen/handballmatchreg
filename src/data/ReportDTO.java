package data;

public class ReportDTO {
	private Suspension suspension;
	private Goal goal;
	
	public ReportDTO(Suspension suspension, Goal goal) {
		this.suspension = suspension;
		this.goal = goal;
	}
	
	public ReportDTO(Suspension suspension) {
		this.suspension = suspension;
	}
	
	public ReportDTO(Goal goal) {
		this.goal = goal;
	}

	public Suspension getSuspension() {
		return suspension;
	}

	public void setSuspension(Suspension suspension) {
		this.suspension = suspension;
	}

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	@Override
	public String toString() {
		return "suspension=" + suspension + ", goal=" + goal;
	}
	
	public String suspensionString() {
		return "Suspension" + ", " + suspension.getId() + ", " + suspension.getMatchId() + ", " + suspension.getMatchTime() + ", " + suspension.getTeamId();
	}
	
	public String goalString() {
		return "Goal" + ", " + goal.getId() + ", " + goal.getMatchId() + ", " + goal.getTimeStamp() + ", " + goal.getTeamId();
	}
}
