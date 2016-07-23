package com.imie.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe représentant une musique en base.
 */
@NamedQueries({ @NamedQuery(name = "Musique.findAll", query = "SELECT m FROM Musique m") })
@Entity
@Table(name = "musique")
public class Musique extends Fichier implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2379255871352181483L;

	/** Constructeur par défaut. */
	public Musique() {
		super();
	}

}