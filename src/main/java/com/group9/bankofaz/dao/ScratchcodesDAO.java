/**
 * 
 */
package com.group9.bankofaz.dao;

import java.util.List;

import com.group9.bankofaz.model.Scratchcodes;

/**
 * @author Anirudh Ruia Gali
 *
 */
public interface ScratchcodesDAO {
	public void add(Scratchcodes scratchcodes); // use saveOrupdate like functionality

	public List<Scratchcodes> get(int scratchid);
}
