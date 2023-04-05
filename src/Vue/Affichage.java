package Vue;

import Model.Batiments.Caserne;
import Model.Map;
import Model.Personnages.Archer;
import Vue.ControllerView.*;

import javax.swing.*;
import java.awt.*;

public class Affichage extends JFrame {
    //private VueController controller;
    private JPanel controller;
    private ArcherController archerController;
    private NoneController noneController;
    private NexusController nexusController;
    private GuerrierController guerrierController;
    private VillageoisController villageoisController;
    private CaserneController caserneController;
    public CardLayout card;

    public Affichage(Map m){
        super("Sunrise Siege");
        VueJeu vueJeu = new VueJeu(m);
        VueRessources vueRessources = new VueRessources(m);
        this.card = new CardLayout();
        new ThreadAfficheur(vueJeu).start();
        this.nexusController = new NexusController(m);
        this.archerController= new ArcherController(m);
        this.noneController=new NoneController(m);
        this.guerrierController=new GuerrierController(m);
        this.villageoisController=new VillageoisController(m);
        this.caserneController = new CaserneController(m);

        String keyArcher = "archer";
        String keyNone = "none";
        String keyNexus = "nexus";
        String keyGuerrier = "guerrier";
        String keyVillageois = "villageois";
        String keyCaserne = "caserne";

        this.controller=new JPanel(card);
        //this.controller=this.archerController;
        //this.controller=new ArcherController(m);
        controller.add(noneController,keyNone);
        controller.add(archerController,keyArcher);
        controller.add(nexusController,keyNexus);
        controller.add(guerrierController,keyGuerrier);
        controller.add(villageoisController,keyVillageois);
        controller.add(caserneController,keyCaserne);

        //this.controller=new ArcherController(m);
        this.add(vueJeu, BorderLayout.CENTER);
        this.add(vueRessources,BorderLayout.SOUTH);
        this.add(controller,BorderLayout.EAST);
        this.setPreferredSize(new Dimension(1300,1000));
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void setArcher(){
        this.controller=this.archerController;
    }

    public void setNone(){
        this.controller=this.noneController;
    }

    public JPanel getController(){
        return this.controller;
    }
}
