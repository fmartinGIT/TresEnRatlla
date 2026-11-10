# **Pràctica: Desenvolupament d'un Tic-Tac-Toe (Tres en ratlla) amb MVVM**

## **Objectiu**

Aprendre a estructurar aplicacions en Android utilitzant **Jetpack Compose** i l'arquitectura **MVVM (Model-View-ViewModel)** amb StateFlow / mutableStateOf . En aquesta pràctica, fareu un anàlisi previ per documentar els components bàsics de MVVM a partir del codi MVVMSimple, el comparareu amb MVVM-separatModel, desenvolupareu el joc del **Tres en ratlla (Tic-Tac-Toe)** i fareu servir **Git i GitHub** per gestionar el projecte i documentar el codi.

## **Fase 1: Anàlisi i Documentació del Patró MVVM (Obligatori)**

Abans de començar a programar el vostre projecte, heu d'analitzar els fitxers de referència proporcionats a classe (MVVMSimple.kt i MVVM-separatModel.kt).

Heu d'incloure un apartat al fitxer README.md del vostre repositori titulat **"1. Anàlisi i documentació de l'arquitectura MVVM"** dividit en dos punts:

### **1.1. Documentació dels components (basant-vos en** MVVMSimple**)**

Expliqueu breument i amb les vostres paraules la responsabilitat de cada capçalera o component de l'arquitectura prenent com a referència l'estructura inicial de MVVMSimple:

* **Model:** Quines dades guarda i quina és la seva funció principal?  
* **ViewModel:** Quina feina fa exactament? Com es comunica amb el Model i com exposa les dades a la interfície?  
* **View (UI / Compose):** Quin és el seu paper? Com rep els canvis d'estat i com notifica les accions de l'usuari al ViewModel?


### **1.2. Anàlisi comparatiu (**MVVMSimple **vs** MVVM-separatModel**)**

Responeu a les preguntes següents:

1. **Estructura i Separació de Responsabilitats:**  
   * Quina diferència hi ha entre la forma d'organitzar el **Model** a MVVMSimple i a MVVM-separatModel?  
   * On s'executa la lògica de negoci del joc (comprovar combinacions, girar cartes, etc.) en cada un dels fitxers?

2. **Immutabilitat i Gestió de l'Estat:**  
   * Com es modifiquen les dades del tauler a MVVM-separatModel? Quin paper juga el mètode .copy() i per què és important per a l'arquitectura reactiva?  
3. **Avantatges de la separació:**  
   * Per què creieu que és millor desconnectar la lògica de negoci (BoardModel) del propi ViewModel a mesura que l'aplicació creix?

### **1.3. Anàlisi comparatiu** 

1) ### `StateFlow` / `MutableStateFlow`

2) ### `mutableStateOf` / `State<T>`

### 

   * Descriu per a que es fan servir els següents mecanismes  
   * Quines són les diferències entre ells  
   * Quin escolliries per l’aplicació i per què?

## **Fase 2: Requeriments del Projecte (Tres en Ratlla)**

Teniu la tasca de desenvolupar un **Tic-Tac-Toe** aplicant la filosofia de MVVM-separatModel.kt (model separat amb lògica pròpia).

### **Model (Lògica de Negoci i Dades)**

* CellModel: Representa una casella (id, posició, símbol "X", "O" o buit).  
* BoardModel:  
  * Conté la llista de caselles, el torn actual, el guanyador o l'estat d'empat i si el joc ha acabat.  
  * Inclou el mètode de fàbrica createInitialBoard().  
  * Conté la lògica pura: makeMove(position: Int) i checkWinner(), retornant instàncies immutables actualitzades amb .copy().  
    

### **ViewModel (Gestió de l'Estat de la UI)**

* GameViewModel:  
  * Manté l'estat reactiu amb MutableStateFlow privat i el publica amb asStateFlow().  
  * Ofereix els mètodes onCellClick(position: Int) i restartGame().

### **View (Interfície d'Usuari amb Jetpack Compose)**

* GameScreen: Pantalla principal que llegeix l'estat amb collectAsState().  
* BoardGrid: Tauler de 3x3 construït amb LazyVerticalGrid o files/columnes.  
* CellItemView: Visualització interactiva de cada casella.  
* Indicadors visuals del torn actual, missatge de victòria/empat i botó de reinici.

## **Fase 3: Requeriments de Git, GitHub i Documentació**

**Contracte "Zero Copy-Paste" \+ documentar:** Proposa't no enganxar mai codi de la IA sense haver-te assegurat que entens cada línia. Si una funció o mètode et sona desconegut (com collectAsState() o asStateFlow()), documenta a la teva pròpia llista o README què fa exactament. Introdueix explicació i fragment de codi simple per testejar com funciona.

1. **Gestió del Repositori:**  
   * Repositori públic a GitHub anomenat Android-MVVM-TicTacToe.  
   * Realitzar commits freqüents i missatges clars (ex: feat: implementa la comprovació de victòria en el BoardModel).  
2. **Estructura del** README.md**:**  
   * **Secció 1:** Anàlisi i documentació de l'arquitectura MVVM (components de MVVMSimple i comparativa amb MVVM-separatModel).  
   * **Secció 2:** Explicació de l'arquitectura utilitzada en el vostre Tres en Ratlla i captura/GIF de l'app en funcionament.  
   * **Secció 3 ("Conceptes i Mètodes Apresos"):** Resum dels mètodes/funcions que heu hagut de cercar o aprendre de nou (ex: collectAsState(), LazyVerticalGrid, asStateFlow(), .copy(), etc.).  
3. **Comentaris al codi:**  
   * Tots els mètodes o estructures d'Android/Compose/StateFlow que siguin nous per a vosaltres han d'estar comentats al codi (// o /\* \*/) explicant amb les vostres paraules la seva funció.

