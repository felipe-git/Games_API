package com.felipe.gamesapp.entities.games.ivoriesOfLier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class IvoriesOfLierGamer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer userId;
	private Integer ivoriesOfLierGameId;
	private boolean playing;
	private Integer order;
	private DecisionType decisionType;
	private Integer decisionNumberOfCubes;
	private Integer decisionCubeValue;
	
	private List<IvoriesOfLierCube> cubes;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIvoriesOfLierGameId() {
		return ivoriesOfLierGameId;
	}

	public void setIvoriesOfLierGameId(Integer ivoriesOfLierGameId) {
		this.ivoriesOfLierGameId = ivoriesOfLierGameId;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
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

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "gamerId", nullable = false)
	private List<IvoriesOfLierCube> getCubes() {
		return cubes;
	}
	
	@SuppressWarnings("unused")
	private void setCubes(List<IvoriesOfLierCube> cubes) {
		this.cubes = cubes;
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
