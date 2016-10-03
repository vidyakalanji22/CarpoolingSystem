package com.edu.notification;

public interface NotificationObservable {

	public void addObserver(NotificationObserver o);

	public void removeObserver(NotificationObserver o);

	public void notifyObserver(String name);
}
