package db2.ufc;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.Set;

public class Util {
	/* (Input Canceled || Invalid Date Format) ? return null : return UserInput*/
	public static LocalDate captureDate(String dateFor) {
        LocalDate result = null;
		String input = JOptionPane.showInputDialog(null, "Enter " + dateFor + "-Date (YYYY-MM-DD):");
		if(input == null) {
			JOptionPane.showMessageDialog(null, "Input canceled");
		} else {
			try {
				result = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Invalid date format");
			}
		}
		return result;
	}
	
	/* (Input Canceled || NotInteger) ? return -1 : return UserInput*/
	public static int captureInt(String intFor) {
		int result = -1;
		String input = JOptionPane.showInputDialog(null, "Enter " + intFor + "-Value (Number):");
		if(input == null) {
			JOptionPane.showMessageDialog(null, "Input canceled");
		} else {
			try {
				result = Integer.parseInt(input);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Invalid input (NaN)");
			}
		}
		return result;
	}

	public static Long captureLong(String longFor) {
		Long result = -1L;
		String input = JOptionPane.showInputDialog(null, "Enter " + longFor + "-Value (Number):");
		if(input == null) {
			JOptionPane.showMessageDialog(null, "Input canceled");
		} else {
			try {
				result = Long.parseLong(input);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Invalid input (NaN)");
			}
		}
		return result;
	}
	
	public static Set<Participant.FightingTechnique> captureFightingTechniques() {
		Set<Participant.FightingTechnique> fts = EnumSet.noneOf(Participant.FightingTechnique.class);
		while(true) {
			String input = JOptionPane.showInputDialog(
	                "Menu:\n" +
	                "1. BOXING\n" +
	                "2. MUAY_THAI\n" +
	                "3. KARATE\n" +
	                "4. JUDO\n" +
	                "5. WRESTLING\n"+
	                "6. RINGEN\n"+
	                "7. KICKBOXING\n" +
	                "8. BRAZILIAN_JIU_JITSU\n"+
	                "9. OK\n" +
	                "Enter your choice:");
	        if (input == null) {
	            JOptionPane.showMessageDialog(null, "Input canceled");
	            continue;
	        }
	        int choice;
	        try {
	            choice = Integer.parseInt(input);
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "Invalid input (NaN)");
	            continue;
	        }
	        switch (choice) {
            case 1:
            	fts.add(Participant.FightingTechnique.BOXING);
                break;
            case 2:
            	fts.add(Participant.FightingTechnique.MUAY_THAI);
                break;
            case 3:
            	fts.add(Participant.FightingTechnique.KARATE);
                break;
            case 4:
            	fts.add(Participant.FightingTechnique.JUDO);
                break;
            case 5:
            	fts.add(Participant.FightingTechnique.WRESTLING);
                break;
            case 6:
            	fts.add(Participant.FightingTechnique.RINGEN);
                break;
            case 7:
            	fts.add(Participant.FightingTechnique.KICKBOXING);
                break;
            case 8:
            	fts.add(Participant.FightingTechnique.BRAZILIAN_JIU_JITSU);
                break;
            case 9:
                return fts;
            default:
                JOptionPane.showMessageDialog(null, "Invalid choice.");
	        }
		}	
	}
}
