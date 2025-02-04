package Galery;

import java.io.Serializable;

import dataStructures.Comparator;

class WorkByTitleComparator implements Comparator<Work>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Work work1, Work work2) {
		return work1.getWorkName().compareTo(work2.getWorkName());
	}

}
