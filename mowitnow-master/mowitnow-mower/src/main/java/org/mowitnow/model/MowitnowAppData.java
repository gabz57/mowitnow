package org.mowitnow.model;

import java.util.List;

/**
 * Root object for mowing a lawn with one or more mower.
 * 
 * @author Arnaud
 *
 */
public class MowitnowAppData {
	/** The lawn to mow. */
	private final Lawn lawn;
	/** A list of data for initializing the mower(s). */
	private final List<MowerInitializationData> mowerInitialDataList;

	/**
	 * Creates the root object containing all the data for mowing a lawn.
	 * 
	 * @param lawn
	 *            the lawn to mow
	 * @param mowerInitialDataList
	 *            one or more set of data for initializing one or more mowers
	 */
	public MowitnowAppData(Lawn lawn, List<MowerInitializationData> mowerInitialDataList) {
		if (lawn == null) {
			throw new IllegalArgumentException("A lawn is required");
		}
		if (mowerInitialDataList == null || mowerInitialDataList.isEmpty()) {
			throw new IllegalArgumentException("At least one mower is required");
		}
		this.lawn = lawn;
		this.mowerInitialDataList = mowerInitialDataList;
	}

	/**
	 * Get the lawn to mow.
	 * 
	 * @return the {@link Lawn} to mow
	 */
	public Lawn getLawn() {
		return lawn;
	}

	/**
	 * Get the list of {@link MowerInitializationData}, containing at least one
	 * element.
	 * 
	 * @return the list {@link MowerInitializationData}
	 */
	public List<MowerInitializationData> getMowerInitialDataList() {
		return mowerInitialDataList;
	}

}
