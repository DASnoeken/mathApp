# import sympy
from sympy import *
M = Matrix([[1,(-152)**1,(-152)**2,(-152)**3,(-152)**4,(-152)**5,-12433170834059388049625217366904424497078480027168808431320574955996836204077014704929114741612381166857922148578179253591636150629159167675816446527682231596665978446762716976127262058659487469473770551226419657774526927094769890043347421005971938813364695566118467489836596605272596306057916608826590238615077035042747753245254859212551756351849910944249891895947895589685082528607254025875358901834731443852094607902799432438901273609254210605446477030563929684538984833098317694874812762050601075624899562581615506377261523131567095603712630878163408027325304974568184727136395330897833351710109561611777036195042897143637519698081418710807302595817956793318668086489053615426924298853913308752245369940091194063917293156604032154787445734758654344930437261839300234525874797577660708526289910388170394941114209024498877852097528996169724842555169936679470125864111234032912788358715631145421371126810164784716989610564883096727613232794614228166608643222598698959414279148320630884719136111401659785121560544134687931185246321166174151394487017738103094310493651303626388832423508954344077638667124933259098755045234031868053004340227010678720232062810259773446174447456745086822174235767488321127436681754355911655134126665184671426602536698173582693663485819136613245446620665827179834443634665494828494198286840079453833875619013751162673227326793227587699137822314641742574224057712151941704380390626812024583353756895485144011293580989255575225031065194251958845702301130642367178849934679806749793573943867492985148125520747005553323677207106091288261579096228340851146077154103479888858296259747669423284538202126220531458669711393212747315081845103001925495004173059124631427842934224196162857899513430069953171610649073367338416249701170690305480887971955910824266387999127185705190638273820119337896393711766409089570213538317489639788719460358975388135298964389281405731184880965040069333037978802355456135478715629840242166386348212820143797399255862574213743794084453563457899350757000145525849832248374237389135753834686549937233585066113527561546983139304740022208828328304062298085028392989085709922582618041327341433400859437889628639166592568865505603745024699692860160647717182263352012667571120056706304876550334999561453467319927783782648575180128895060063314062480956786755649339220921407732561459172243800671182609773194420759912117],[1,(-385)**1,(-385)**2,(-385)**3,(-385)**4,(-385)**5,-1292131405769450554687951520047068084558501229458719869453319001792988402746517542460262274582341440201238307127935103609762625788685537175058028951266359939919911125452883652432429394228269910806147206646625419850639267153001019468011953567764583459056025749196076307256586162838606452345364059863928290349725298620059940813377887502424737914573022598911504032920148303331799418512315154847885531020844510141794483517566669937098247118669859849851696370476617461541800977251895419763992503096169801356447276018756046314900006745323984735616494623073254163345822135643986809178942754204797469088026683318631506730837030347109458404173851023973572032538410613531235952094996189261900394092710918019140505588401729971560489792140943756174838749074061073592612576760998270144833927603562836033641277921692581580456270954966773402209019805258726817422748539860750946677672643068044287016314872889752584727536012436045149219523761776127009399283923191074098159329322221663422417331475726112516667944823180392632840894301422614225233931030603114233459570707514043792644752530705551064911449052725657820916217182675298433189998736703378326200476665212289001521439739775930387733088935781386297096905235851594045275967459456152128339695808597218351052702708622452481764322324866709094485379268461588867362818166632187073006581445849046666583841935353386254194447045982996443919159047677943029168240416416260454075549059172467173950527366130036611434738621985464272985272093919096510739030451567690054017864379473130775522146012240771287993106810472312642800281893581051664652174740279412251562469726924430840987711705643091950045111478304610462403172006425010245303299124028157031700592916452346808047889039219472982016064988709383208016543245640330773386823695137414091708324518160826879746516773669165450331173185660274417097385638206235230058391540381847081305225342425823283254398949566628337829009526667393975352570652474686494144298311611216833222815263315928025033449098485217914657908634831255593342047945478118665897354593726614396548371472549086545520394866476206536823988135890455340814961177420146942540985249477660229171716932050643938752911499717990055518910014505423520594708030897745586987069123941617126771475488476640820273181633373266334500601082698968662102258089896594956808771837923583809743931800408129882142161182584557862396080944542384802917715],[1,(618)**1,(618)**2,(618)**3,(618)**4,(618)**5,13724330999341152973966078514024423026696924714773120912612488494702940145005837641150838259173051853181232174059055651986970351380426989263028299117177083260097248934579133531295136420774998149642923988505628523397765878863222880185009945616648174816863399982979287815599999015519351214489868459123902817758203344045050773534904933964497397102322617988114282994155825960118516686270047942158457109053283961993326275161398611286501145803910049113940731823421949094506677525542345447124344648239266243844727902732901337781078871650814631865155936518262433258121384396134076290363172786074927097463718373202733371193570362726013917739911382706674632831120169026063065985654991066195906508270030541759563874873267403201895182047029637302022379622328123034396423956230031706624364761355968565450989153876611862617954517164138979785407890897685444637007416211218938793938051967509697655552604434142613679852544920082014353518583996027257331787996691066259418143025347266488597757297099001070238794084574628281757060092224999627185783382775261212380307023908675347677792060272949291881065299927631977500159224504660480036674326543712157191289511981778704944494409682332827411092675182590046886765168399686681453785917120837358431595797859286155772650908499027770489373024779012700632595829951120785048524292128733363535803248459139334667631905613540317640643675028709450962048114811269503301274637519091526016267680284386245925163247172162440584205956232825421683022050171496783056066399111877281733496758842920506395076776807417629001906733771851915730142769785226300862957213823218350106569785596983652581061241779234477802826612126149191903628489723008707874531433223447513356416231759879633158545577204087257800158381382017089839970517340584058416173665242132704951837724611778205030629173888116541682943107495742959263010566283098689745549113249727008980127367929504040864826659011713292845283117882269536728258390885025262056516349099474018319712710657375893478194001710507872760769685845654186452540861755283148171451448179773222485578914460272062986166523405463304669317975733754769403280593833907665265353359878142757170300570469228169898122826105383041425995836821300606964320520593594626993193997249444226299311173191809308905619387811449093816856598884606487095304541694582955714434278087671016864283932786049811920362941544443808901054694535927611424151433],[1,(244)**1,(244)**2,(244)**3,(244)**4,(244)**5,131412278465552368609018965897657281057559774252756816882790266325580336052036202007486413999991403907044728086954203361624662483614848996733812827903307602734452024007805093677617976929446183407949533084375057243351821965873645355940428521573263124003902407734229962987360938654416015210400338700112006005822084670640104394522223585877622022970133436882683158886585426779117494252494567358544051649557491549146635745736827034101233008216112886337019701693492542795090970365029802492611646869024668216678295579219898898623753783693101068842770519723219946928905544428888718583886770498659707030925160528869982356438766661224096617489298176619480442851868845491472662509052749720940216707235910970641796192818363854111178567445527681628745917617081796103387454453907920639612527975802498165777897208592659145696804872239945740130875294234724550910706812380270576512524890968615969313136418000747323845837798202123449564270262219640820164628535646005872435329127172946597704301126945391053984693519819911692552329888594172645057442188390138645045463698424353636154735571766311653984771331170691023512609163575570868927115199032654983743144245913223076175943448102038843971906963724327403251374299784710165183662709264681383872489643941106898715300171356440326169436369076506525943052921277555207680023639728820005362771103773063055754164331987956763282646065176359457448938394575903711308629998081477122838493286148166225610040986139646521750963935783759208698089948243879732909426306925621871685358325100575714978833420589469953387453408233465724323118369285649719740776763292041521789579997707093210690847112104231588374429620472972328289482764362394315697224918982572030062357173126963859783033776234282149303790817301696210115004917439296618318585286304917039298893653224660425928089839106676982392837587698438633885329701751062593697709058923036035581003859906575105743414466911839799534884868481996101999036977810253756520965937235309176488740598861561795918883823826760219473116930300044243581031385362093003896454813029332080326845330796844918071353929116509221791592600788764063297036977168173281293740374396679538077656755414927023515084429806945482039273940148150083471669056847826594078696225312960714069867623741297123908499138240065617493431065879967348296741828536804712413543222038234294356767091932430235211586105690365940996471725489603324887631],[1,(-334)**1,(-334)**2,(-334)**3,(-334)**4,(-334)**5,-635142950324711195294536398907590537175442978487287250791076898139024342145271097623376525810728314625547661293899403780306988798027587981649149936917745245031439052624844839201294786848294099034046047985251296136052114429915914460092034846166894429836299419100618914239269119896259637240269849027745213823253188737437960131008652605803122056530144148559052992842298837932061707470943130618323855527047476062809955785439953046076492238956084523043644147876125456037587081963603519158748980128699145764400412692079027737630557472475539803589254617435504307282664875495624036648977882906513891992155413242964685080083030445875242453445582116380060466217318060855019404242894465624487100058827613617226239844709800909742692509967418948582394463690825398792516998008631916313784343296528169248320691768602641987934259451408811211919252478894290948588660464250860961207600886228015001403845827186578573248742495369660247435627185656935985092172207817505822124116452630288320041428848348701049003942109954696083909260839876967639020074940038421521967307419413818746880934530648850753517416218032472686666038220981483315575803658219766728847599608511281825459060001380964117092275487964670904077113975398188891712395013985507765741112295963409879164924969621642601486071375244641792031925935132825632716732499418559813695291579326600932563461654494612537339774157506269800156380330784650002795071660015220930062923568211008486539210940425687525129228126608735633440367058726486242371609367141114572090465250050685710260711722914677030039870329756614707426590745302172055252850074041627427887320829213881782072178480756814239484317080656661888603203158840822211668430415518639928311354433951797205246911079917263116104672901533850214363848590224537046328776013740115673161081568019924821858441284851310649986005244985531303478264653274537552510776251306266318622880642260350561836847443110251554780469354684126347775833400563232153478336969257705405058463667623672018656830763197734547327975713901259296435316978168953298493960822499928051432096911839542392763559237126394529642640201966122376591017123118994727348920197197071590519582874536777275279765428880833058433994182345533755282181340287258902589802481663543551092937358893450110754101313836229660491897770536340540797040039730286233927255296815227194375319419569394083939523213125395457855188963042043946264383],[1,(93)**1,(93)**2,(93)**3,(93)**4,(93)**5,1051394165416950559462684849766424255160348948471713568765616849327616602799358639309557552576623183740713707738811380898999835048501164650551434738988145725753850728210474734774551228995904491229514432076899313903357021524773427645925857726130573084698535906167086455116546702745333633911571565099485001885271546566296660753572101930488212698175450277835341058077796122291731643846664099436422058477282257692479457633468923183379842102015397195206596455322147515400129659286544533069505862608095723621628712529302210532064988777739271067409707776698158311354428183555460696665719628611495489966692384240211489181588112545420483557045939378757091350368084234637539156665140736588516651576277345413813483144170702798868548926712478608203211572497292373465605660080361596819321872580634685205847550089752035744557678009922426280777877098982889513796608409503670491140168289836124436138049739013395300757214408074575207163632094776025853865119362435482543621892560904777985882913273539978900767707473998404886896555890471334278862765395809741156316330027054629278508956747781588285449391711930542594971591737668271596422701590369159457600110234578140354746612586545690310144750526122457027720563283663114808330007920945853272682927937972991162634115109132610181554525675381290658958058373726282493452087596841575035367117757310722974760468503863789915756058204785771053862063889380700035343198254286790891779796665483223477954697963786379013188642699507720021435034879438589459550588555431430742961018329396634435402932401997028366778960193697500652505435274052305658774417366881946403604038223844278083488440006407907275688798680469841090140451403968723205084880641998991644946774764385817556353812979426378619563995828327260044185085930340905006446823632553064750488014729739837098980226950441315757436358076987863498550643022187363817415602647587307441961998254840566801679535794393424084991935561045815877799223283359125377139093873883605999122495380304214827833096503380362804854310036548014095118594348230188831583239944251120750497708379600055370635475991788655048277415724047026940699145301935144538404773731157345940938924725183911302102638784714874867357944112762670317643611541685230073488776293456652361049781410648458707449717074686255902175017572597796592347749642085853486103481914461779200386648373543496767873732005172438316401694074008690537433]])
print("Matrix : {} ".format(M))
# Use sympy.rref() method
M_rref = M.rref()
print("The Row echelon form of matrix M and the pivot columns : {}".format(M_rref))
