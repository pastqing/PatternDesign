public class MyObserver implements Observer {

	Subject subject;

	// get the notify message from Concentrate Subject
	@Override
	public void update() {
		String message = subject.getMessage();
		System.out.println("From Subject " + subject.getClass().getName()
				+ " message: " + message);
	}

	@Override
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	// subcirbe some Subject
	public void subscribe(String subjectName) {
		SubjectManagement.getInstance().getSubject(subjectName).register(this);
	}

	// cancel subcribe
	public void cancelSubcribe(String subjectName) {
		SubjectManagement.getInstance().getSubject(subjectName).remove(this);
	}

}
