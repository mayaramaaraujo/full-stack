import styled from "styled-components";

const NoteFormContainer = styled.div`
    width: 24rem;
    height: 2rem;
    display: flex;
    gap: 0.2rem;
    margin: 1rem;

    & input {
      width: 70%;
      border-radius: 0.5rem;  

    }

    & button {
      border-radius: 0.5rem;  
    }
  `

const NoteForm = ({ inputValue, handleInputChange, handleAddNote }) => {

  return (
    <NoteFormContainer>
      <input
        type="text"
        value={inputValue}
        onChange={handleInputChange}
        placeholder="Enter your note"
      />
      <button onClick={handleAddNote}>Add Note</button>
    </NoteFormContainer>
  )
}

export default NoteForm;