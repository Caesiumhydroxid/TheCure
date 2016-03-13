package com.jonas.thecuring.cancerGame;

import com.badlogic.gdx.math.MathUtils;
import com.jonas.thecuring.util.Observable;

public class Model extends Observable {
	
	public float progress=0;
	private float probabilityDetected;
	public int credits=0;
	
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
	
	public int khSimilarityToBodycells=2;
	public int khDestroyIncomingImunecells=3;
	public int kaMoreBloodvessels=1;
	public int kaMutations=1;
	public int kaFastCellDivision=2;
	public int kaDangerousMutations=2;
	public int kaMetastases=5;
	public int kaAttacksTissue=2;
	public int kdAntiTumorSuppressor=1;
	public int kdShield=1;
	public int kdInvincibleForBody=5;
	public int kdRoubustnessVsMedicine=2;
	public int kdStrengthVsMedicine=4;
	public int kdHypoxia=2;
	
	private float growthRate;
	private float antiHealRate;
	private float antiMedicineHealRate;
	private boolean medicineActive=false;
	
	private float medicineHealRate;
	private float healRate;
	
	private float timeFactor=1;
	private float elapsedTime;
	public boolean sentMessage=false;
	public int day=0;
	private int creditsSpentOnDay5;
	public boolean willDie=false;
	private float willDieTime;
	private String message;
	public Model()
	{
	}
	public void tick(float delta)
	{
		growthRate = (aMetastases * 10f + aMutations * 1.5f + aDangerousMutations * 2f + aFastCellDivision *2f+aAttacksTissue)/99.5f; //Normalized to 1
		
		probabilityDetected = timeFactor - (hSimilarityToBodycells*2 + hDestroyIncomingImunecells*3)/45 + growthRate*0.75f;
		antiHealRate = (dAntiTumorSuppressor * 1f + dShield *1.3f + 5f * dInvincibleForBody)/45.7f;
		antiMedicineHealRate = (dRoubustnessVsMedicine * 2f + dStrengthVsMedicine *3f)/45f;
		
		elapsedTime += delta;
		healRate = (float) (Math.exp(elapsedTime/50.f)-1);
		progress = ((day+1)/5f)*(growthRate+antiHealRate+antiMedicineHealRate)/2f + MathUtils.random(-1, 1)*0.01f+willDieTime/20;
		if(sentMessage)
		{
			if(willDie)
			{
				willDieTime+=delta;
			}
			else
			{
				willDieTime-=delta;
			}
		}
		
		if(day<4)
		{
			if(progress>0.8f)
			{
				progress = 0.8f;
			}
		}
		if(day>=4)
		{
			if(!sentMessage)
			{
				if(creditsSpentOnDay5>10&&growthRate+antiHealRate+antiMedicineHealRate>0.5)
				{
					willDie = true;
				}
			}
		}
		setChanged();
		notifyObservers();
	}
	class outputPair{
		public String s;
		public int value;
	}
	public void processEvent(String name)
	{
		  if(name.equals( "h_similarity_to_bodycells"))
		  {
			 hSimilarityToBodycells=incrementVariable(hSimilarityToBodycells, khSimilarityToBodycells);
			 }
		 else if(name.equals( "h_destroy_incoming_imunecells")){
			 hDestroyIncomingImunecells=incrementVariable(hDestroyIncomingImunecells, khDestroyIncomingImunecells);
			 }
		 else if(name.equals( "a_more_bloodvessels")){
			 aMoreBloodvessels=incrementVariable(aMoreBloodvessels, kaMoreBloodvessels);
			 }
		 else if(name.equals( "a_mutations")){
			 aMutations=incrementVariable(aMutations, kaMutations);
			 }
		 else if(name.equals( "a_fast_cell_division")){
			 aFastCellDivision=incrementVariable(aFastCellDivision, kaFastCellDivision,aMoreBloodvessels,5);
			 }
		 else if(name.equals( "a_dangerous_mutations")){
			 aDangerousMutations=incrementVariable(aDangerousMutations, kaDangerousMutations,aMutations,5);
			 }
		 else if(name.equals( "a_metastases")){
			 aMetastases=incrementVariable(aMetastases, kaMetastases,aFastCellDivision,5,aDangerousMutations,5,5);
			 }
		 else if(name.equals( "a_attacks_tissue")){
			 aAttacksTissue=incrementVariable(aAttacksTissue, kaAttacksTissue);
			 }
		 else if(name.equals( "d_anti_tumor_suppressor")){
			 dAntiTumorSuppressor=incrementVariable(dAntiTumorSuppressor, kdAntiTumorSuppressor);
			 }
		 else if(name.equals( "d_Shield")){
			 dShield=incrementVariable(dShield, kdShield);
			 }
		 else if(name.equals( "d_invincible_for_body")){
			 dInvincibleForBody=incrementVariable(dInvincibleForBody, kdInvincibleForBody,dAntiTumorSuppressor,5,dShield,5,5);
			 }
		 else if(name.equals( "d_robustness_vs_medicine")){
			 dRoubustnessVsMedicine=incrementVariable(dRoubustnessVsMedicine, kdRoubustnessVsMedicine);
			 }
		 else if(name.equals( "d_strength_vs_medicine")){
			 dStrengthVsMedicine=incrementVariable(dStrengthVsMedicine, kdStrengthVsMedicine,dRoubustnessVsMedicine,5);
			 }
		 else if(name.equals( "d_hypoxia")){
			 dHypoxia=incrementVariable(dHypoxia, kdHypoxia);
			 }
		 setChanged();
		 notifyObservers(message);
		 message = null;
	}
	
	void addCredit()
	{
		credits++;
		setChanged();
		notifyObservers();
	}
	
	private int incrementVariable(int variable,int cost)
	{
		return incrementVariable(variable, cost, 0, 0, 0, 0,9);
	}
	
	private int incrementVariable(int variable,int cost,int value1,int minValue1)
	{
		return incrementVariable(variable, cost, value1, minValue1, 0, 0,9);
	}
	
	private int incrementVariable(int variable,int cost,int value1,int minValue1,int maxVariableValue)
	{
		return incrementVariable(variable, cost, value1, minValue1, 0, 0,maxVariableValue);
	}
	
	private int incrementVariable(int variable,int cost,int value1,int minValue1,int value2,int minValue2)
	{
		return incrementVariable(variable, cost, value1, minValue1, value2, minValue2,9);
	}
	
	private int incrementVariable(int variable,int cost,int value1,int minValue1,int value2,int minValue2,int maxVariableValue)
	{
		message = null;
		if(value1>=minValue1&&value2>=minValue2)
		{
			if(variable<maxVariableValue)
			{
				if(credits>=cost)
				{
					credits -= cost;
					if(day>=4&&!sentMessage)
					{
						creditsSpentOnDay5+=cost;
					}
					variable++;
				}
				else
				{
					message = "Nicht genügend Credits!";
				}
			}
			else
			{
				message = "Maximales Level!";
			}
		}
		else
		{
			message = "Anforderungen nicht erfüllt";
		}
		return variable;
	}
}
