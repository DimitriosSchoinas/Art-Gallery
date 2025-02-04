package Galery;

import java.io.Serializable;
import dataStructures.*;

abstract class AbstractUserClass implements User, Serializable, UserSetMethods {

	private static final long serialVersionUID = 1L;
	protected String login;
	protected String name;
	protected int age;
	protected String email;
	protected List<Bid> activeBids;

	public AbstractUserClass(String login, String name, int age, String email) {

		this.login = login;
		this.name = name;
		this.age = age;
		this.email = email;
		this.activeBids = new DoubleList<Bid>();
	}

	@Override
	public String getUserName() {
		return name;
	}

	@Override
	public int getAge() {

		return age;
	}

	@Override
	public String getEmail() {

		return email;
	}

	@Override
	public String getLogin() {

		return login;
	}

	@Override
	public void addActiveBid(Bid bid) {
		activeBids.addLast(bid);

	}

	@Override
	public void removeActiveBid(Bid bid) {
		activeBids.remove(bid);
	}

	@Override
	public boolean hasAnyActiveBids() {
		return activeBids.isEmpty();
	}
}
