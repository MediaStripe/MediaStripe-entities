package com.imie.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe représentant une photo en base.
 */
@NamedQueries({ @NamedQuery(name = "Photo.findAll", query = "SELECT p FROM Photo p") })
@Entity
@Table(name = "photo")
public class Photo extends Fichier implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5978036614769849713L;

	/** Constructeur par défaut. */
	public Photo() {
		super();
	}
}