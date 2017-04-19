package Controller;
import Model.*;
import View.*;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameController{

	private ArrayList<Observer> observers;
	
	private int mode;
	private int timer;
	private int difficulty;
	private int boardMode;
	private String playerWhite;
	private String playerBlack;
	private int piecesSelected;
	private String singleName;
	
	@FXML
	private BorderPane singlePlayerScene;
	@FXML
	private BorderPane multiplayerScene;
	@FXML
	private BorderPane networkScene;
	@FXML
	private ToggleButton normalMode;
	@FXML
	private ToggleButton speedMode;
	@FXML
	private ToggleGroup modeGroup;
	@FXML
	private ToggleButton whitePieceButton;
	@FXML
	private ToggleButton blackPieceButton;
	@FXML
	private ToggleGroup piecesGroup;
	@FXML
	private Slider diffSlider;
	@FXML
	private TextField singlePlayerNameText;
	@FXML
	private TextField whitePlayerNameText;
	@FXML
	private TextField blackPlayerNameText;
	@FXML
	private TextField timerText;
	@FXML
	private Button sButton;
	@FXML
	private Button mButton;
	@FXML
	private Button netButton;
	@FXML

	private Button playButton;

	private CheckBox randomBox;

	
	//Game settings 
	private static final int SPEEDMODE = 10;
	private static final int NORMALMODE = 11;
	
	private static final int WHITEPIECES = 12;
	private static final int BLACKPIECES = 13;
	
	//Scene Modes
	public static final String MAINMENU = "../View/FXMLFiles/MainMenu.fxml";
	public static final String SMENU = "../View/FXMLFiles/SingleGameMenu.fxml";
	public static final String MMENU = "../View/FXMLFiles/MultiplayerGameMenu.fxml";
	public static final String NMENU = "../View/FXMLFiles/NetworkGameMenu.fxml";
	public static final String GAMEVIEW = "../View/FXMLFiles/GameView.fxml";

	
	
	public GameController(){
		observers = new ArrayList<Observer>();
		mode = NORMALMODE;
		boardMode = Board.NORMAL;
		timer = 0;
	}

	public void handleMenus(ActionEvent e) throws IOException{

		if(e.getSource() == sButton ){
			showScene(SMENU,sButton);
			piecesSelected = WHITEPIECES;
		}else if(e.getSource() == mButton){
			showScene(MMENU,mButton);
		}else if(e.getSource() == netButton){
			showScene(NMENU,netButton);
		}
	}
	
	public void startGame(ActionEvent e)throws IOException{
		if(e.getSource() == playButton){
			Scene currentScene = playButton.getScene();
			if(singlePlayerScene != null && currentScene == singlePlayerScene.getScene()){
				startSinglePlayerGame();
				playSingleGame();
			}else if(multiplayerScene != null && currentScene == multiplayerScene.getScene()){
				startMultiplayerGame();
				playMultiplayerGame();
			}else if(networkScene != null && currentScene == networkScene.getScene()){
				startNetworkGame();
				playNetworkGame();
			}
			showScene(GAMEVIEW,playButton);
		}
	}
	


	public void showScene(String location,Button butt) throws IOException{
		Stage stage = (Stage) butt.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(location));
		stage.setScene(new Scene(root,800,500));
		stage.show();
		
	}

	
	private void startSinglePlayerGame() {
		
	}
	
	public void startMultiplayerGame(){
		
	}
	
	public void startNetworkGame(){
		
	}

	
	public void selectGameMode(){
		if(normalMode.isSelected()){
			mode = NORMALMODE;
			System.out.println("normal");
			timerText.setDisable(true);
			
		}
		else if (speedMode.isSelected()) {
			mode = SPEEDMODE;
			System.out.println("speed");	
			timerText.setDisable(false);
		}
	}
	
	public void selectPieces(){
		if(whitePieceButton.isSelected()){
			piecesSelected = WHITEPIECES;
			System.out.println("normal");
		}
		else if (blackPieceButton.isSelected()){
			piecesSelected = BLACKPIECES;
			System.out.println("speed");		
		}
	}

	public void setRandom(){
		if(randomBox.isSelected()) boardMode = Board.RANDOM;
		else boardMode = Board.NORMAL;
	}
	
	public void setSinglePlayerName(){
		singleName = singlePlayerNameText.getText();
		System.out.println(singleName);
	}
	
	public void setDifficulty(){
		difficulty = (int) diffSlider.getValue();
		System.out.println(difficulty);
	}
	
	public void setTimer(){
		String newValue = timerText.getText();
		 if (!newValue.matches("\\d*")) {
		 	String newString = newValue.replaceAll("[^\\d]", "");
	        timerText.setText(newString);
	        if(containsDigits(newString)){
	        	timer = Integer.parseInt(newString);
	        }   
		 }else{
			 timer = Integer.parseInt(newValue);
		 }
		System.out.println(timer);
	}
	
	private boolean containsDigits(String s) {
	    boolean containsDigits = false;

	    if (s != null && !s.isEmpty()) {
	        for (char c : s.toCharArray()) {
	            if (containsDigits = Character.isDigit(c)) {
	                break;
	            }
	        }
	    }
	    return containsDigits;
	}
	
	public void setWhitePlayerName(){
		playerWhite = whitePlayerNameText.getText();
		System.out.println(playerWhite);
	}
	
	public void setBlackPlayerName(){
		playerBlack = blackPlayerNameText.getText();
		System.out.println(playerBlack);
	}
	
	public void playSingleGame(){
		if(piecesSelected == WHITEPIECES){
			AbstractPlayer LocalPlayer = new LocalPlayer(singleName,Color.WHITE);	
			AbstractPlayer AIPlayer = new AIPlayer("AIPlayer",Color.BLACK,difficulty);
			startGame(LocalPlayer,AIPlayer);
		}
		else if(piecesSelected == BLACKPIECES)
		{
			AbstractPlayer LocalPlayer = new LocalPlayer(singleName,Color.BLACK);	
			AbstractPlayer AIPlayer = new AIPlayer("AIPlayer",Color.WHITE,difficulty);
			startGame(AIPlayer,LocalPlayer);
		}
		
	}
	private void startGame(AbstractPlayer whitePlayer,AbstractPlayer blackPlayer){
		if(mode == NORMALMODE){
			GameDriver game = new GameDriver(whitePlayer,blackPlayer,GameDriver.HISTORYENABLED,boardMode);
			game.playGame(3);
		}
		else if(mode == SPEEDMODE){
			GameDriver game = new GameDriver(whitePlayer,blackPlayer,GameDriver.HISTORYENABLED,boardMode,timer);
			game.playGame(3);
		}
	}
	
	public void playMultiplayerGame(){
		AbstractPlayer whitePlayer = new LocalPlayer(playerWhite,Color.WHITE);
		AbstractPlayer blackPlayer = new LocalPlayer(playerBlack,Color.BLACK);
		if(mode == NORMALMODE){
			GameDriver game = new GameDriver(whitePlayer, blackPlayer ,GameDriver.HISTORYDISABLED,boardMode);
			game.playGame(3);
		}
		else if(mode == SPEEDMODE){
			GameDriver game = new GameDriver(whitePlayer,blackPlayer,GameDriver.HISTORYDISABLED,boardMode,timer);
			game.playGame(3);
		}
	}
	
	public void playNetworkGame(){
		
	}
	
	
}
