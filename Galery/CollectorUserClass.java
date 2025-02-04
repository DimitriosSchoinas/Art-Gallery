package Galery;

class CollectorUserClass extends AbstractUserClass implements CollectorUser {

	private static final long serialVersionUID = 1L;

	public CollectorUserClass(String login, String name, int age, String email) {
		super(login, name, age, email);
	}
}
