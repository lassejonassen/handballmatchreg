package data;

public class ReportDTO 
{
	private Suspension suspension;
	private Goal goal;
	
	public ReportDTO(Suspension suspension,Goal goal)
	{
		this.suspension = suspension;
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
}
