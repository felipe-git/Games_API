package com.felipe.gamesapp.entities.games.ivoriesOfLier;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class IvoriesOfLierMove {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
	private IvoriesOfLierGame game;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
	private IvoriesOfLierPlayer player;

	private DecisionType decisionType;
	private Integer decisionNumberOfCubes;
	private Integer decisionCubeValue;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public IvoriesOfLierGame getGame() {
		return game;
	}
	public void setGame(IvoriesOfLierGame game) {
		this.game = game;
	}

	public IvoriesOfLierPlayer getPlayer() {
		return player;
	}
	public void setPlayer(IvoriesOfLierPlayer player) {
		this.player = player;
	}

	public DecisionType getDecisionType() {
		return decisionType;
	}
	public void setDecisionType(DecisionType decisionType) {
		this.decisionType = decisionType;
	}

	public Integer getDecisionNumberOfCubes() {
		return decisionNumberOfCubes;
	}
	public void setDecisionNumberOfCubes(Integer decisionNumberOfCubes) {
		this.decisionNumberOfCubes = decisionNumberOfCubes;
	}

	public Integer getDecisionCubeValue() {
		return decisionCubeValue;
	}
	public void setDecisionCubeValue(Integer decisionCubeValue) {
		this.decisionCubeValue = decisionCubeValue;
	}
    
	public enum DecisionType {
		AT_LEAST(DecisionType.AT_LEAST_VALUE),
		LESS_THAN(DecisionType.LESS_THAN_VALUE),
		EXACTLY(DecisionType.EXACTLY_VALUE);
		
		public static final char AT_LEAST_VALUE = 'A';
		public static final char LESS_THAN_VALUE = 'L';
		public static final char EXACTLY_VALUE = 'E';
		
		public final char value;
		private static final Map<Character, DecisionType> values;
		static {
			values = new HashMap<Character, DecisionType>();
			for(DecisionType state : values()) {
				values.put(state.value, state);
			}
		}

		private DecisionType(char value) {
			this.value = value;
		}
		public Character getValue() {
			return value;
		}

		public static final char encode(DecisionType type) {
			return type.value;
		}
		public static final DecisionType decode(char value) {
			return values.get(value);
		}
	}
}
