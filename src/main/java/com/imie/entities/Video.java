package com.imie.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe représentant une vidéo en base.
 */
@NamedQueries({ @NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v") })
@Entity
@Table(name = "video")
public class Video extends Fichier implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1444745384092428370L;

	/** Constructeur par défaut. */
	public Video() {
		super();
	}

}