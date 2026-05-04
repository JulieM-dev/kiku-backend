package com.mongault.kiku.component;

import com.mongault.kiku.dto.RegisterDto;
import com.mongault.kiku.factory.CardReviewFactory;
import com.mongault.kiku.model.*;
import com.mongault.kiku.repository.CardRepository;
import com.mongault.kiku.repository.DeckRepository;
import com.mongault.kiku.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final CardReviewFactory cardReviewFactory;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(ApplicationArguments args) {
        if (deckRepository.count() > 0) {
            log.info("Database already initialized, skipping.");
            return;
        }

        log.info("Initializing database with sample data...");


        User testUser = userRepository.save(User.builder()
                .username("Test User")
                .email("test@mail.com")
                .password(passwordEncoder.encode("123456"))
                .build());

        Deck jlptN5Deck = deckRepository.save(Deck.builder()
                .name("JLPT N5 vocabulaire")
                .description("Vocabulaire japonais de base")
                .user(testUser)
                .build());


        Deck phraseDeck = deckRepository.save(Deck.builder()
                .name("JLPT N5 phrases")
                .description("Phrases japonaises de base")
                .user(testUser)
                .build());

        Deck casualPhraseDeck = deckRepository.save(Deck.builder()
                .name("JLPT N5 phrases casual")
                .description("Phrases japonaises casual")
                .user(testUser)
                .build());


        // -----------------------------------------------------------------------
        // IMPORTANT NOTE : I generated the code below (between the 2 comments) and the sample dataset thanks to ChatGPT
        // but still checked it manually. It can still have errors though.
        //
        // Prompt used :
        // "Génère du code Java utilisant le pattern builder (Card.builder()) pour créer une List<Card> de flashcards japonais type Anki pour des decks nommés : jlptN5Deck, phraseDeck et  casualPhraseDeck.
        // Il faut passer la variable de deck dans chaque entrées .
        //Chaque carte doit être construite avec Card.builder() et inclure les champs suivants :
        //
        //japanese – le texte japonais (kanji/kana)
        //kana – la lecture en kana
        //romaji – la prononciation romaji
        //translation – la traduction en français
        //formalityLevel – sois FormalityLevel.POLITE, FormalityLevel.CASUAL ou rien si neutre
        //deck – la variable du deck
        //
        //Génère plusieurs cartes pour débutants (un deck de vocabulaire, de phrases et de phrases entre amis)
        // -----------------------------------------------------------------------

        List<Card> n5Cards = List.of(
                Card.builder().japanese("おはようございます").kana("おはようございます").romaji("Ohayou gozaimasu").translation("Bonjour (matin)").formalityLevel(FormalityLevel.POLITE).deck(jlptN5Deck).build(),
                Card.builder().japanese("水").kana("みず").romaji("Mizu").translation("Eau").deck(jlptN5Deck).build(),
                Card.builder().japanese("こんにちは").kana("こんにちは").romaji("Konnichiwa").translation("Bonjour (journée)").deck(jlptN5Deck).build(),
                Card.builder().japanese("食べ物").kana("たべもの").romaji("Tabemono").translation("Nourriture").deck(jlptN5Deck).build(),
                Card.builder().japanese("こんばんは").kana("こんばんは").romaji("Konbanwa").translation("Bonsoir").deck(jlptN5Deck).build(),
                Card.builder().japanese("人").kana("ひと").romaji("Hito").translation("Personne").deck(jlptN5Deck).build(),
                Card.builder().japanese("おやすみなさい").kana("おやすみなさい").romaji("Oyasumi nasai").translation("Bonne nuit").formalityLevel(FormalityLevel.POLITE).deck(jlptN5Deck).build(),
                Card.builder().japanese("日").kana("ひ").romaji("Hi").translation("Jour / Soleil").deck(jlptN5Deck).build(),
                Card.builder().japanese("ありがとうございます").kana("ありがとうございます").romaji("Arigatou gozaimasu").translation("Merci beaucoup").formalityLevel(FormalityLevel.POLITE).deck(jlptN5Deck).build(),
                Card.builder().japanese("時間").kana("じかん").romaji("Jikan").translation("Temps / Heure").deck(jlptN5Deck).build(),
                Card.builder().japanese("すみません").kana("すみません").romaji("Sumimasen").translation("Excusez-moi").formalityLevel(FormalityLevel.POLITE).deck(jlptN5Deck).build(),
                Card.builder().japanese("家").kana("いえ").romaji("Ie").translation("Maison").deck(jlptN5Deck).build(),
                Card.builder().japanese("いただきます").kana("いただきます").romaji("Itadakimasu").translation("Bon appétit (avant de manger)").deck(jlptN5Deck).build(),
                Card.builder().japanese("犬").kana("いぬ").romaji("Inu").translation("Chien").deck(jlptN5Deck).build(),
                Card.builder().japanese("はい").kana("はい").romaji("Hai").translation("Oui").deck(jlptN5Deck).build(),
                Card.builder().japanese("猫").kana("ねこ").romaji("Neko").translation("Chat").deck(jlptN5Deck).build(),
                Card.builder().japanese("いいえ").kana("いいえ").romaji("Iie").translation("Non").deck(jlptN5Deck).build(),
                Card.builder().japanese("本").kana("ほん").romaji("Hon").translation("Livre").deck(jlptN5Deck).build(),
                Card.builder().japanese("わかりました").kana("わかりました").romaji("Wakarimashita").translation("J'ai compris").formalityLevel(FormalityLevel.POLITE).deck(jlptN5Deck).build(),
                Card.builder().japanese("学校").kana("がっこう").romaji("Gakkou").translation("École").deck(jlptN5Deck).build(),
                Card.builder().japanese("おねがいします").kana("おねがいします").romaji("Onegaishimasu").translation("S'il vous plaît").formalityLevel(FormalityLevel.POLITE).deck(jlptN5Deck).build(),
                Card.builder().japanese("友達").kana("ともだち").romaji("Tomodachi").translation("Ami").deck(jlptN5Deck).build(),
                Card.builder().japanese("どうぞ").kana("どうぞ").romaji("Douzo").translation("Je vous en prie / Allez-y").deck(jlptN5Deck).build(),
                Card.builder().japanese("電車").kana("でんしゃ").romaji("Densha").translation("Train").deck(jlptN5Deck).build(),
                Card.builder().japanese("ごちそうさまでした").kana("ごちそうさまでした").romaji("Gochisousama deshita").translation("Merci pour le repas").formalityLevel(FormalityLevel.POLITE).deck(jlptN5Deck).build(),
                Card.builder().japanese("お金").kana("おかね").romaji("Okane").translation("Argent").deck(jlptN5Deck).build(),
                Card.builder().japanese("はじめまして").kana("はじめまして").romaji("Hajimemashite").translation("Enchanté").formalityLevel(FormalityLevel.POLITE).deck(jlptN5Deck).build(),
                Card.builder().japanese("仕事").kana("しごと").romaji("Shigoto").translation("Travail").deck(jlptN5Deck).build(),
                Card.builder().japanese("よろしくおねがいします").kana("よろしくおねがいします").romaji("Yoroshiku onegaishimasu").translation("Ravi de vous rencontrer").formalityLevel(FormalityLevel.POLITE).deck(jlptN5Deck).build(),
                Card.builder().japanese("今日").kana("きょう").romaji("Kyou").translation("Aujourd'hui").deck(jlptN5Deck).build()
        );


        List<Card> phraseCards = List.of(
                Card.builder().japanese("私は学生です").kana("わたしはがくせいです").romaji("Watashi wa gakusei desu").translation("Je suis étudiant").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("これは何ですか").kana("これはなんですか").romaji("Kore wa nan desu ka").translation("Qu'est-ce que c'est ?").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("トイレはどこですか").kana("トイレはどこですか").romaji("Toire wa doko desu ka").translation("Où sont les toilettes ?").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("いくらですか").kana("いくらですか").romaji("Ikura desu ka").translation("Combien ça coûte ?").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("日本語が少し分かります").kana("にほんごがすこしわかります").romaji("Nihongo ga sukoshi wakarimasu").translation("Je comprends un peu le japonais").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("もう一度言ってください").kana("もういちどいってください").romaji("Mou ichido itte kudasai").translation("Pouvez-vous répéter s'il vous plaît ?").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("英語が話せますか").kana("えいごがはなせますか").romaji("Eigo ga hanasemasu ka").translation("Parlez-vous anglais ?").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("水をください").kana("みずをください").romaji("Mizu o kudasai").translation("De l'eau s'il vous plaît").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("電車は何時ですか").kana("でんしゃはなんじですか").romaji("Densha wa nanji desu ka").translation("À quelle heure est le train ?").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("助けてください").kana("たすけてください").romaji("Tasukete kudasai").translation("Aidez-moi s'il vous plaît").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("名前は何ですか").kana("なまえはなんですか").romaji("Namae wa nan desu ka").translation("Comment vous appelez-vous ?").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("私の名前はモンゴー・ジュリーです").kana("わたしのなまえはモンゴー・ジュリーです").romaji("Watashi no namae wa Mongoo Julie desu").translation("Je m'appelle Julie Mongault").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("日本はどこですか").kana("にほんはどこですか").romaji("Nihon wa doko desu ka").translation("Où est le Japon ?").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("この近くにコンビニはありますか").kana("このちかくにコンビニはありますか").romaji("Kono chikaku ni konbini wa arimasu ka").translation("Y a-t-il un convenience store près d'ici ?").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("メニューを見せてください").kana("メニューをみせてください").romaji("Menyuu o misete kudasai").translation("Montrez-moi le menu s'il vous plaît").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("お会計をお願いします").kana("おかいけいをおねがいします").romaji("Okaikei o onegaishimasu").translation("L'addition s'il vous plaît").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("この電車は東京に行きますか").kana("このでんしゃはとうきょうにいきますか").romaji("Kono densha wa Toukyou ni ikimasu ka").translation("Ce train va-t-il à Tokyo ?").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("病院はどこですか").kana("びょういんはどこですか").romaji("Byouin wa doko desu ka").translation("Où est l'hôpital ?").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("日本語を勉強しています").kana("にほんごをべんきょうしています").romaji("Nihongo o benkyou shite imasu").translation("J'étudie le japonais").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("すき焼きが食べたいです").kana("すきやきがたべたいです").romaji("Sukiyaki ga tabetai desu").translation("Je voudrais manger du sukiyaki").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("今日はいい天気ですね").kana("きょうはいいてんきですね").romaji("Kyou wa ii tenki desu ne").translation("Il fait beau aujourd'hui, n'est-ce pas ?").deck(phraseDeck).build(),
                Card.builder().japanese("どこから来ましたか").kana("どこからきましたか").romaji("Doko kara kimashita ka").translation("D'où venez-vous ?").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("フランスから来ました").kana("フランスからきました").romaji("Furansu kara kimashita").translation("Je viens de France").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("これを一つください").kana("これをひとつください").romaji("Kore o hitotsu kudasai").translation("Un de ceux-ci s'il vous plaît").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build(),
                Card.builder().japanese("もっとゆっくり話してください").kana("もっとゆっくりはなしてください").romaji("Motto yukkuri hanashite kudasai").translation("Parlez plus lentement s'il vous plaît").formalityLevel(FormalityLevel.POLITE).deck(phraseDeck).build()
        );

        List<Card> casualPhraseCards = List.of(
                Card.builder().japanese("学生だよ").kana("がくせいだよ").romaji("Gakusei da yo").translation("Je suis étudiant").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("これなに？").kana("これなに？").romaji("Kore nani ?").translation("C'est quoi ça ?").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("トイレどこ？").kana("トイレどこ？").romaji("Toire doko ?").translation("C'est où les toilettes ?").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("いくら？").kana("いくら？").romaji("Ikura ?").translation("C'est combien ?").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("日本語ちょっとわかる").kana("にほんごちょっとわかる").romaji("Nihongo chotto wakaru").translation("Je comprends un peu le japonais").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("もう一回言って").kana("もういっかいいって").romaji("Mou ikkai itte").translation("Répète s'il te plaît").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("英語話せる？").kana("えいごはなせる？").romaji("Eigo hanaseru ?").translation("Tu parles anglais ?").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("水ちょうだい").kana("みずちょうだい").romaji("Mizu choudai").translation("Tu peux me donner de l'eau ?").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("電車何時？").kana("でんしゃなんじ？").romaji("Densha nanji ?").translation("Le train c'est à quelle heure ?").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("助けて！").kana("たすけて！").romaji("Tasukete !").translation("Aide-moi !").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("名前は？").kana("なまえは？").romaji("Namae wa ?").translation("C'est quoi ton prénom ?").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("モンゴー・ジュリーだよ").kana("モンゴー・ジュリーだよ").romaji("Mongoo Jurii da yo").translation("Je m'appelle Julie Mongault").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("この辺にコンビニある？").kana("このへんにコンビニある？").romaji("Kono hen ni konbini aru ?").translation("Y a un convenience store par ici ?").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("メニュー見せてくれる？").kana("メニューみせてくれる？").romaji("Menyuu misete kureru ?").translation("Montre-moi le menu").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("この電車、東京行く？").kana("このでんしゃ、とうきょういく？").romaji("Kono densha, Toukyou iku ?").translation("Ce train va à Tokyo ?").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("病院どこ？").kana("びょういんどこ？").romaji("Byouin doko ?").translation("C'est où l'hôpital ?").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("日本語勉強してる").kana("にほんごべんきょうしてる").romaji("Nihongo benkyou shiteru").translation("J'étudie le japonais").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("すき焼き食べたい").kana("すきやきたべたい").romaji("Sukiyaki tabetai").translation("Je voudrais manger du sukiyaki").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("今日いい天気だね").kana("きょういいてんきだね").romaji("Kyou ii tenki da ne").translation("Il fait beau aujourd'hui, hein ?").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("どこから来たの？").kana("どこからきたの？").romaji("Doko kara kita no ?").translation("Tu viens d'où ?").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("フランスから来たよ").kana("フランスからきたよ").romaji("Furansu kara kita yo").translation("Je viens de France").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("これ一つちょうだい").kana("これひとつちょうだい").romaji("Kore hitotsu choudai").translation("Un de ceux-ci s'il te plaît").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("もっとゆっくり話して").kana("もっとゆっくりはなして").romaji("Motto yukkuri hanashite").translation("Parle plus lentement").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("お腹すいた").kana("おなかすいた").romaji("Onaka suita").translation("J'ai faim").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("眠い").kana("ねむい").romaji("Nemui").translation("J'ai sommeil").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("やばい").kana("やばい").romaji("Yabai").translation("Incroyable / C'est chaud").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("マジで？").kana("マジで？").romaji("Maji de ?").translation("Sérieusement ?").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("うそ！").kana("うそ！").romaji("Uso !").translation("C'est pas vrai !").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("いいね").kana("いいね").romaji("Ii ne").translation("C'est bien / Super").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build(),
                Card.builder().japanese("いいよ").kana("いいよ").romaji("Ii yo").translation("De rien / OK / pas de problème").formalityLevel(FormalityLevel.CASUAL).deck(casualPhraseDeck).build()
        );

        // -----------------------------------------------------------------------
        // IMPORTANT NOTE : Generated code above
        // -----------------------------------------------------------------------

        // Save cards and init reviews
        saveCardsWithReviews(n5Cards);
        saveCardsWithReviews(phraseCards);
        saveCardsWithReviews(casualPhraseCards);

        log.info("Database initialized");
    }

    private void saveCardsWithReviews(List<Card> cards) {
        long order = 0;
        for (Card card : cards) {
            card.getReviews().addAll(cardReviewFactory.createInitialReviews(card));
            card.setInitialOrder(order++);
            cardRepository.save(card);
        }
    }
}