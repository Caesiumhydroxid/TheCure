package com.jonas.thecuring;

import java.util.Observable;

public class Model extends Observable {
	
	private float progress;
	private float probabilityDetected;
	private int credits;
	
	public int hSimilarityToBodycells;
	public int hDestroyIncomingImunecells;
	public int aMoreBloodvessels;
	public int aMutations;
	public int aFastCellDivision;
	public int aDangerousMutations;
	public int aMetastases;
	public int aAttacksTissue;
	public int dAntiTumorSuppressor;
	public int dShield;
	public int dInvincibleForBody;
	public int dRoubustnessVsMedicine;
	public int dStrengthVsMedicine;
	public int dHypoxia;
	
	public int khSimilarityToBodycells;
	public int khDestroyIncomingImunecells;
	public int kaMoreBloodvessels;
	public int kaMutations;
	public int kaFastCellDivision;
	public int kaDangerousMutations;
	public int kaMetastases;
	public int kaAttacksTissue;
	public int kdAntiTumorSuppressor;
	public int kdShield;
	public int kdInvincibleForBody;
	public int kdRoubustnessVsMedicine;
	public int kdStrengthVsMedicine;
	public int kdHypoxia;
	
	public Model()
	{
		
	}
	public void tick(float delta)
	{
		
	}
	
	
	public void processEvent(String name)
	{
		 switch(name)
		 {
		 case "h_similarity_to_bodycells":
			 incrementHSimilarityToBodycells();
			 break;
		 case "h_destroy_incoming_imunecells":
			 incrementHDestroyIncomingImunecells();
			 break;
		 case "a_more_bloodvessels":
			 incrementAMoreBloodvessels();
			 break;
		 case "a_mutations":
			 incrementAMutations();
			 break;
		 case "a_fast_cell_division":
			 incrementAFastCellDivision();
			 break;
		 case "a_dangerous_mutations":
			 incrementADangerousMutations();
			 break;
		 case "a_metastases":
			 incrementAMetastases();
			 break;
		 case "a_attacks_tissue":
			 incrementAAttacksTissue();
			 break;
		 case "d_anti_tumor_suppressor":
			 incrementDAntiTumorSuppressor();
			 break;
		 case "d_Shield":
			 incrementDShield();
			 break;
		 case "d_invincible_for_body":
			 incrementDInvisibleForBody();
			 break;
		 case "d_robustness_vs_medicine":
			 incrementDRoubustnessVsMedicine();
			 break;
		 case "d_strength_vs_medicine":
			 incrementDStrengthVsMedicine();
			 break;
		 case "d_hypoxia":
			 incrementDHypoxia();
			 break;
		 }
		 setChanged();
		 notifyObservers();
	}
	void incrementHSimilarityToBodycells()
	{
		hSimilarityToBodycells++;
	}
	void incrementHDestroyIncomingImunecells()
	{
		hDestroyIncomingImunecells++;
	}
	void incrementAMoreBloodvessels()
	{
		aMoreBloodvessels++;
	}
	void incrementAFastCellDivision()
	{
		aFastCellDivision++;
	}
	void incrementADangerousMutations()
	{
		aDangerousMutations++;
	}
	void incrementAMetastases()
	{
		aMetastases++;
	}
	void incrementAMutations()
	{
		aMutations++;
	}
	void incrementAAttacksTissue()
	{
		aAttacksTissue++;
	}
	void incrementDAntiTumorSuppressor()
	{
		dAntiTumorSuppressor++;
	}
	void incrementDShield()
	{
		dShield++;
	}
	void incrementDInvisibleForBody()
	{
		dInvincibleForBody++;
	}
	void incrementDRoubustnessVsMedicine()
	{
		dRoubustnessVsMedicine++;
	}
	void incrementDStrengthVsMedicine()
	{
		dStrengthVsMedicine++;
	}
	void incrementDHypoxia()
	{
		dHypoxia++;
	}
}
